let currentSessionId = null
let currentStudentId = null
let selectedRating = null
const studentsData = {} // Declare studentsData variable

function openStudentList(sessionId) {
  currentSessionId = sessionId
  const panel = document.getElementById("studentListPanel")
  const tbody = document.getElementById("studentTableBody")
  const students = studentsData[sessionId] || []

  tbody.innerHTML = ""
  students.forEach((student, index) => {
    const row = document.createElement("tr")
    const statusClass = student.participated === "Có" ? "participated" : "not-participated"
    const statusText = student.participated === "Có" ? "Tham gia" : "Vắng mặt"
    const isEvaluated = student.rating ? true : false

    row.innerHTML = `
            <td>${index + 1}</td>
            <td>${student.mssv}</td>
            <td>${student.name}</td>
            <td><span class="student-status ${statusClass}">${statusText}</span></td>
            <td class="evaluation-cell">
                ${
                  isEvaluated
                    ? `<span class="eval-rating">${student.rating}</span>`
                    : `<button class="btn-evaluate-student" onclick="openEvaluationForm(${sessionId}, ${student.id})">Đánh giá</button>`
                }
            </td>
        `
    tbody.appendChild(row)
  })

  panel.classList.add("open")
}

function closeStudentList() {
  document.getElementById("studentListPanel").classList.remove("open")
}

function openEvaluationForm(sessionId, studentId) {
  currentSessionId = sessionId
  currentStudentId = studentId
  selectedRating = null

  const students = studentsData[sessionId]
  const student = students.find((s) => s.id === studentId)

  const infoDisplay = document.getElementById("studentInfoDisplay")
  infoDisplay.innerHTML = `
        <div class="student-info-item">
            <span class="student-info-label">MSSV:</span>
            <span class="student-info-value">${student.mssv}</span>
        </div>
        <div class="student-info-item">
            <span class="student-info-label">Họ và tên:</span>
            <span class="student-info-value">${student.name}</span>
        </div>
        <div class="student-info-item">
            <span class="student-info-label">Tình trạng:</span>
            <span class="student-info-value">${student.participated === "Có" ? "Tham gia" : "Vắng mặt"}</span>
        </div>
    `

  document.getElementById("evaluationDetail").value = student.comment || ""

  document.querySelectorAll(".rating-btn").forEach((btn) => {
    btn.classList.remove("selected")
  })

  if (student.rating) {
    const ratingBtn = Array.from(document.querySelectorAll(".rating-btn")).find(
      (btn) => btn.textContent === student.rating,
    )
    if (ratingBtn) {
      ratingBtn.classList.add("selected")
      selectedRating = student.rating
    }
  }

  document.getElementById("evaluationModal").classList.add("open")
}

function closeEvaluationForm() {
  document.getElementById("evaluationModal").classList.remove("open")
  selectedRating = null
  currentStudentId = null
}

function selectRating(rating) {
  selectedRating = rating
  document.querySelectorAll(".rating-btn").forEach((btn) => {
    btn.classList.remove("selected")
    if (btn.textContent === rating) {
      btn.classList.add("selected")
    }
  })
}

function submitEvaluation(event) {
  event.preventDefault()

  if (!selectedRating) {
    alert("Vui lòng chọn mức đánh giá")
    return
  }

  const comment = document.getElementById("evaluationDetail").value.trim()
  if (!comment) {
    alert("Vui lòng nhập đánh giá chi tiết")
    return
  }

  const students = studentsData[currentSessionId]
  const student = students.find((s) => s.id === currentStudentId)

  student.rating = selectedRating
  student.comment = comment

  alert("Đánh giá đã được lưu thành công!")
  closeEvaluationForm()
  openStudentList(currentSessionId)
}
