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

/* SIDEBAR */

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

.schoolSidebarLogo {
    font-size: 1.6rem;
    font-weight: 600;
    background: #23294b;
    border-radius: 20px;
    padding: 0 15px;
}

/* MENU TITLES */

.schoolSidebarMenuTitle{
  color:#9ca3c7;
  font-size:12px;
  letter-spacing:1px;
  margin:22px 10px 12px;
  font-weight:600;
}

/* MENU */

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

/* HOVER */

.schoolSidebarMenuItem:hover{
  background:#242b4d;
  color:#ffffff;
}

/* ACTIVE */

.schoolSidebarActive{
  background:#4d44cc;
  color:#ffffff;
}

.schoolSidebarMenuItem {
    /* Your default styling for menu items */
    transition: background-color 0.2s ease;
}

/* This targets the container when active */
.schoolSidebarMenuItem.schoolSidebarActive {
    background-color: #34495e; /* Change to your preferred highlight color */
}

/* Ensures the text color inside the active container looks right */
.schoolSidebarMenuItem.schoolSidebarActive a {
    color: #ffffff; 
    font-weight: bold;
}

/* DARK MODE */

.schoolSidebarDark{
  background:#050814;
}

.schoolSidebarDark .schoolSidebarMenuItem:hover{
  background:#1c2445;
}

/* RESPONSIVE */

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

    <!-- LOGO -->
    <div class="schoolSidebarTop">

      <div class="schoolSidebarLogo">
        School Management System.
      </div>

      <!-- MAIN MENU -->
      <div class="schoolSidebarMenuTitle">
        MAIN
      </div>

      <ul class="schoolSidebarMenuList">

        <li class="schoolSidebarMenuItem schoolSidebarActive">
          <a href="attendance.jsp">Attendance</a>
        </li>
        
        <li class="schoolSidebarMenuItem schoolSidebarActive">
          <a href="profile.jsp">Profile</a>
        </li>

      </ul>
    </div>

    <!-- BOTTOM -->
    <div class="schoolSidebarBottom">
      <ul class="schoolSidebarMenuList">
        <li class="schoolSidebarMenuItem">
          <a href="/School_Management_System/login/login.jsp">Logout</a>
        </li>
      </ul>
    </div>
  </aside>
  <script>

  document.addEventListener("DOMContentLoaded", function () {
    const currentPath = window.location.pathname.split("/").pop();
    const menuLinks = document.querySelectorAll(".schoolSidebarMenuList a");

    menuLinks.forEach(link => {
        const linkPath = link.getAttribute("href");

        if (currentPath === linkPath) {
            document.querySelectorAll(".schoolSidebarMenuItem").forEach(item => {
                item.classList.remove("schoolSidebarActive");
            });
            link.closest("li").classList.add("schoolSidebarActive");
        }
    });
});

  </script>

</body>
</html>