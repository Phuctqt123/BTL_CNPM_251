// Initialize application on page load
document.addEventListener("DOMContentLoaded", () => {
  renderSessions()
})

const sessionsList = [] // Declare sessionsList variable

function renderSessions() {
  const grid = document.getElementById("sessionsGrid")
  grid.innerHTML = ""

  sessionsList.forEach((session) => {
    const card = document.createElement("div")
    card.className = `session-card ${session.className}`
    card.setAttribute("data-filter", session.status)

    const statusBadgeClass = session.status === "upcoming" ? "upcoming" : "completed"
    const statusText = session.status === "upcoming" ? "Sắp Diễn Ra" : "Đã Kết Thúc"

    const actionBtnText = session.status === "completed" ? "Đánh Giá" : "Hủy"
    const actionBtnClass = session.status === "completed" ? "btn-evaluate" : "btn-cancel"
    const actionOnclick = session.status === "completed" ? `onclick="openStudentList(${session.id})"` : `onclick=""`

    card.innerHTML = `
            <div class="session-header">
                <div class="session-title">${session.title}</div>
                <span class="status-badge ${statusBadgeClass}">${statusText}</span>
            </div>
            <div class="session-info">
                <div class="info-row">
                    <span class="info-label">Giảng viên:</span>
                    <span class="info-value">${session.instructor}</span>
                </div>
                <div class="info-row">
                    <span class="info-label">Ngày:</span>
                    <span class="info-value">${session.date}</span>
                </div>
                <div class="info-row">
                    <span class="info-label">Thời gian:</span>
                    <span class="info-value">${session.time}</span>
                </div>
                <div class="info-row">
                    <span class="info-label">Địa điểm:</span>
                    <span class="info-value">${session.location}</span>
                </div>
                <div class="info-row">
                    <span class="info-label">Số lượng:</span>
                    <span class="info-value">${session.students} sinh viên</span>
                </div>
            </div>
            <div class="actions">
                <button class="action-btn btn-download" onclick="openDocumentsManagement(${session.id})">Tài Liệu</button>
                <button class="action-btn ${actionBtnClass}" ${actionOnclick}>${actionBtnText}</button>
            </div>
        `

    grid.appendChild(card)
  })
}
