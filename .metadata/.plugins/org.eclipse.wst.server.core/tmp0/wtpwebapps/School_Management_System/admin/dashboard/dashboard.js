/**
 * 
 */

window.onload = function () {
    loadDashboard();
};

function loadDashboard() {
    fetch('/School_Management_System/AdminDashboard')
    .then(response => {
        if (!response.ok) {
            throw new Error("HTTP error! Status: " + response.status);
        }
        return response.json();
    })
    .then(data => {
        // COUNTS (fallback to 0 if null/undefined)
        document.getElementById("totalTeachers").textContent = data.totalTeachers || 0;
        document.getElementById("totalStudents").textContent = data.totalStudents || 0;
        document.getElementById("totalClasses").textContent = data.totalClasses || 0;

        // TABLES (fallback to empty array if data lacks keys)
        renderRecentTeachers(data.recentTeachers || []);
        renderRecentStudents(data.recentStudents || []);
    })
    .catch(error => console.error("Failed to load dashboard data:", error));
}


// RECENT TEACHERS

function renderRecentTeachers(teachers) {

    const tableBody =
        document.getElementById("recentTeachersBody");

    tableBody.innerHTML = "";

    teachers.forEach(teacher => {

        const row = document.createElement("tr");

        row.innerHTML = `

            <td>${teacher.full_name}</td>

            <td>${teacher.subject}</td>

            <td>
                <span class="badge active-badge">
                    Active
                </span>
            </td>

        `;

        tableBody.appendChild(row);

    });

}


// RECENT STUDENTS

function renderRecentStudents(students) {

    const tableBody =
        document.getElementById("recentStudentsBody");

    tableBody.innerHTML = "";

    students.forEach(student => {

        const row = document.createElement("tr");

        row.innerHTML = `

            <td>${student.full_name}</td>

            <td>
                ${student.class_name || "—"}
                ${student.section ? "- " + student.section : ""}
            </td>

            <td>
                <span class="badge enrolled-badge">
                    Enrolled
                </span>
            </td>

        `;

        tableBody.appendChild(row);

    });

}