let currentSessionCardIndex = null
const documents = [] // Declare the documents variable

function openDocumentsManagement(sessionIndex) {
  const docsPanel = document.getElementById("documentsManagement")
  const docsContainer = document.getElementById("documentsContainer")

  currentSessionCardIndex = sessionIndex
  docsContainer.innerHTML = ""

  const docList = documents[sessionIndex] || []
  docList.forEach((doc, index) => {
    const tag = document.createElement("div")
    tag.className = "document-item-tag"
    tag.innerHTML = `
            ${doc}
            <button class="delete-doc" onclick="deleteDocument(${sessionIndex}, ${index})" title="Xóa tài liệu">×</button>
        `
    docsContainer.appendChild(tag)
  })

  docsPanel.classList.add("open")
}

function closeDocumentsManagement() {
  document.getElementById("documentsManagement").classList.remove("open")
  currentSessionCardIndex = null
}

function addNewDocument() {
  if (!currentSessionCardIndex) return

  const docName = prompt("Nhập tên tài liệu:")
  if (docName && docName.trim()) {
    documents[currentSessionCardIndex].push(docName.trim())
    openDocumentsManagement(currentSessionCardIndex)
  }
}

function deleteDocument(sessionIndex, index) {
  documents[sessionIndex].splice(index, 1)
  openDocumentsManagement(sessionIndex)
}
