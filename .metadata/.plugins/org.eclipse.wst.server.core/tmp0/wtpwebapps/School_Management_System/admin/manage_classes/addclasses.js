/**
 *  window.onload = function() {
      fetch("/School_Management_System/AddClasses")
          .then(response => response.json())
          .then(data => console.log(data))
          .catch(error => console.error('Fetch error:', error));
 }
 */

window.onload = function() {
    fetch("/School_Management_System/AddClasses")
        .then(response => response.json())
        .then(data => {
            const gridContainer = document.querySelector('.classCardsGrid');
            gridContainer.innerHTML = '';

            data.forEach(classData => {
                const cardHTML = `
                     <div class="classCardContainer" data-id="${classData.class_id}">
                         <div class="classCardTopDecoration"></div>
                         <div class="classStudentCountBadge">0 students</div>
                         <h2 class="classCardTitle">${classData.class_name} - ${classData.section}</h2>
                         <p class="classCardSectionText">Section ${classData.section}</p>
                         <p class="classCardInfo">Capacity: ${classData.capacity}</p>
                         <p class="classCardInfo">Room: ${classData.room_number}</p>
                         <div class="classCardDivider"></div>
                     </div>
                 `;

                gridContainer.insertAdjacentHTML('beforeend', cardHTML);
            });
        })
        .catch(error => console.error('Fetch error:', error));
}


const classModal = document.getElementById("classModal");
const openClassModalButton = document.getElementById("openClassModalButton");
const closeClassModalButton = document.getElementById("closeClassModalButton");
const cancelClassModalButton = document.getElementById("cancelClassModalButton");
// OPEN MODAL
openClassModalButton.addEventListener("click", () => {
    classModal.classList.add("active");
});
// CLOSE MODAL
closeClassModalButton.addEventListener("click", () => {
    classModal.classList.remove("active");
});
cancelClassModalButton.addEventListener("click", () => {
    classModal.classList.remove("active");
});
// CLOSE ON OUTSIDE CLICK
classModal.addEventListener("click", (event) => {
    if (event.target === classModal) {
        classModal.classList.remove("active");
    }
});

// create class sending class details to servlet 
const addTeacher = document.getElementById("createClass");
addTeacher.addEventListener("click", (e) => {
    e.preventDefault();
    const classes = {
        className: document.getElementById("className").value,
        section: document.getElementById("section").value,
        capacity: document.getElementById("capacity").value,
        roomNumber: document.getElementById("roomNumber").value
    }

    console.log(classes);

    fetch('/School_Management_System/AddClasses', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(classes)
    })
        .then(response => response.json())
        .then(data => alert("Success: " + data.message))
        .catch(error => {
            alert("Error: Failed to save class details");
            console.error("error", error)
        });
    document.getElementById("addClassForm").reset();
    window.location.reload();
});