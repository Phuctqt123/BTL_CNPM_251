function filterSessions(filter) {
  document.querySelectorAll(".filter-btn").forEach((btn) => {
    btn.classList.remove("active")
  })
  event.target.classList.add("active")

  const cards = document.querySelectorAll(".session-card")
  cards.forEach((card) => {
    if (filter === "all") {
      card.style.display = "block"
    } else if (filter === "upcoming" && card.classList.contains("upcoming-card")) {
      card.style.display = "block"
    } else if (filter === "completed" && card.classList.contains("completed")) {
      card.style.display = "block"
    } else {
      card.style.display = "none"
    }
  })
}

function searchSessions() {
  const searchTerm = document.getElementById("searchInput").value.toLowerCase()
  const cards = document.querySelectorAll(".session-card")

  cards.forEach((card) => {
    const title = card.querySelector(".session-title").textContent.toLowerCase()
    if (title.includes(searchTerm)) {
      card.style.display = "block"
    } else {
      card.style.display = "none"
    }
  })
}
