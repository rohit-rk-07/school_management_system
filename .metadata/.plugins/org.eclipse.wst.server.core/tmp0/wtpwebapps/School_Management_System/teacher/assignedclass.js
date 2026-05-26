const loggedInUser =
 JSON.parse(
     localStorage.getItem(
         "loggedInUser"
     )
 );

window.onload = function () {loadAssignedClasses();};

function loadAssignedClasses(){
    const teacherId = loggedInUser.referenceId;

    fetch( "/School_Management_System/AssignedClass?teacherId=" + teacherId)
    .then(response => response.json())
    .then(data => { renderAssignedClasses( data.classes );
    })
    .catch(error => { console.error(error);
    });
}


function renderAssignedClasses(classes){

    const grid = document.querySelector(".assignedClassesGrid");
    
    if (!grid) {
        console.error("Could not find the container element '.assignedClassesGrid' on the page.");
        return;
    }

    grid.innerHTML = "";

    classes.forEach(classesData => {
        const card = document.createElement("div");
        card.classList.add("assignedClassesCard");

        card.innerHTML = `
            <span class="assignedClassesBadge">
                ${classesData.subject_name}
            </span>

            <h2 class="assignedClassesTitle">
                ${classesData.class_name} - ${classesData.section}
            </h2>

            <p class="assignedClassesSubject">
                Subject: ${classesData.subject_name}
            </p>

            <p class="assignedClassesInfo">
                Students: ${classesData.total_students}
            </p>

            <p class="assignedClassesInfo">
                Room: ${classesData.room_number}
            </p>
        `;
        grid.appendChild(card);
    });
}