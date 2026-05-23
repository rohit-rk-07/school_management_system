<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>School Sidebar</title>

  <style type="text/css">
  *{
  margin:0;
  padding:0;
  box-sizing:border-box;
}

body{
  font-family:'Poppins',sans-serif;
  background:#f4f5fa;
}

.schoolSidebar{
  width:280px;
  height:100vh;
  background:#11162d;
  color:#ffffff;
  padding:20px 14px;
  display:flex;
  flex-direction:column;
  justify-content:space-between;
  /*border-top-right-radius:20px;
  border-bottom-right-radius:20px;*/
  transition:0.3s;
}

.schoolSidebarLogo{
  border-radius:14px;
  background:#23294b;
  display:flex;
  align-items:start;
  justify-content:start;
  font-size:20px;
  margin-bottom:35px;
}

.schoolSidebarMenuTitle{
  color:#9ca3c7;
  font-size:12px;
  letter-spacing:1px;
  margin:22px 10px 12px;
  font-weight:600;
}

.schoolSidebarMenuList{
  list-style:none;
}

.schoolSidebarMenuItem{
  display:flex;
  align-items:center;
  gap:14px;
  padding:10px 16px;
  border-radius:14px;
  margin-bottom:10px;
  color:#b6bdd9;
  cursor:pointer;
  transition:0.3s;
  font-size:15px;
  font-weight:500;
}

.schoolSidebarMenuItem a{
  font-size:16px;
  text-decoration: none;
  color: #fff;
  width: 100%;
  height: 100%;
}

.schoolSidebarMenuItem:hover{
  background:#242b4d;
  color:#ffffff;
}

.schoolSidebarActive{
  background:#4d44cc;
  color:#ffffff;
}

.schoolSidebarMenuItem {

    transition: background-color 0.2s ease;
}


.schoolSidebarMenuItem.schoolSidebarActive {
    background-color: #34495e; 
}

.schoolSidebarMenuItem.schoolSidebarActive a {
    color: #ffffff; 
    font-weight: bold;
}

@media(max-width:768px){

  .schoolSidebar{
    width:100%;
    height:auto;
    border-radius:0;
  }

}
  </style>

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
</head>

<body>

  <aside class="schoolSidebar">

    <div class="schoolSidebarTop">

      <div class="schoolSidebarLogo">
        School Management System.
      </div>

      <div class="schoolSidebarMenuTitle">
        MAIN
      </div>

      <ul class="schoolSidebarMenuList">

        <li class="schoolSidebarMenuItem schoolSidebarActive">
          <a href="/School_Management_System/admin/dashboard/dashboard.jsp">Dashboard</a>
        </li>

        <li class="schoolSidebarMenuItem">
          <a href="/School_Management_System/admin/manage_teacher/addteacher.jsp">Teacher</a>
          
        </li>

        <li class="schoolSidebarMenuItem">
          <a href="/School_Management_System/admin/manage_student/addstudent.jsp">Students</a>
        </li>

        <li class="schoolSidebarMenuItem">
          <a href="/School_Management_System/admin/manage_classes/addclasses.jsp">Classes</a>
        </li>

      </ul>

      <div class="schoolSidebarMenuTitle">
        ASSIGNMENTS
      </div>

      <ul class="schoolSidebarMenuList">

        <li class="schoolSidebarMenuItem">
          <a href="/School_Management_System/admin/assign_student/assignStudent.jsp">Assign Student</a>
        </li>

        <li class="schoolSidebarMenuItem">
          <a href="/School_Management_System/admin/assign_teacher/assignTeacher.jsp">Assign Teacher</a>
        </li>

      </ul>

      <div class="schoolSidebarMenuTitle">
        ACADEMICS
      </div>

      <ul class="schoolSidebarMenuList">

        <li class="schoolSidebarMenuItem">
          <a href="/School_Management_System/admin/attendance/attendance.jsp">Attendance</a>
        </li>

        <li class="schoolSidebarMenuItem">
          <a href="/School_Management_System/admin/results/results.jsp">Results</a>
        </li>

      </ul>

    </div>

    <div class="schoolSidebarBottom">

      <ul class="schoolSidebarMenuList">

        <li class="schoolSidebarMenuItem">
          Logout
        </li>

      </ul>

    </div>

  </aside>

  <script>
  
  document.addEventListener("DOMContentLoaded", function () {
    const currentPath = window.location.pathname;
    const menuLinks = document.querySelectorAll(".schoolSidebarMenuList a");

    menuLinks.forEach(link => {
        const linkPath = link.getAttribute("href");

        if (currentPath.endsWith(linkPath)) {
            document.querySelectorAll(".schoolSidebarMenuItem").forEach(item => {
                item.classList.remove("schoolSidebarActive");
            });
            
            link.closest(".schoolSidebarMenuItem").classList.add("schoolSidebarActive");
        }
    });
});
  
  </script>

</body>
</html>