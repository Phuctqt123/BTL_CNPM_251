const express = require('express');
const axios = require('axios');
const bodyParser = require('body-parser');
const https = require('https');
const cors = require('cors');

const app = express();
const port = 8087;           // Backend service URL
const casHost = 'https://casserver-production.up.railway.app'; // CAS server

app.use(bodyParser.json());
app.use(cors()); // Cho phép frontend localhost

// Endpoint nhận ST từ frontend
app.post('/userinfo', async (req, res) => {
  const { ticket, service } = req.body;
  console.log('[Backend] Received request for user info. Ticket:', ticket, 'Service:', service);

  if (!ticket || !service) return res.status(400).json({ error: 'Missing ticket or service URL' });

  try {
    const validateUrl = `${casHost}/cas/serviceValidate?service=${encodeURIComponent(service)}&ticket=${encodeURIComponent(ticket)}`;
    console.log('[Backend] Validating ST with CAS:', validateUrl);

    const response = await axios.get(validateUrl, {
      httpsAgent: new https.Agent({ rejectUnauthorized: false }),
      validateStatus: () => true // để log mọi status
    });

    console.log('[Backend] CAS response status:', response.status);
    console.log('[Backend] CAS response body:', response.data);

    if (response.status !== 200) return res.status(response.status).json({ error: 'CAS validation returned non-200 status' });

    const xml = response.data;
    if (xml.includes('<cas:authenticationFailure')) {
      return res.status(401).json({ error: 'ST validation failed: ' + xml.match(/<cas:authenticationFailure.*?>(.*?)<\/cas:authenticationFailure>/)[1] });
    }

    // Parse XML đơn giản
    const parseTag = (tag) => {
      const match = xml.match(new RegExp(`<cas:${tag}>(.*?)<\\/cas:${tag}>`));
      return match ? match[1] : null;
    };

    const userInfo = {
      username: parseTag('username'),
      keyuser: parseTag('keyuser'),
      role: parseTag('role'),
    };

    console.log('[Backend] Parsed user info:', parseTag);
    res.json({ success: true, attributes: userInfo });

  } catch (err) {
    console.error('[Backend] CAS validation error:', err.message);
    res.status(500).json({ error: 'CAS validation exception' });
  }
});

app.listen(port, () => console.log(`Local service running on http://localhost:${port}`));
