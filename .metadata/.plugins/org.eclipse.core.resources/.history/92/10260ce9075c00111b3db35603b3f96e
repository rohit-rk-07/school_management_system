<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Student List</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">

    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
        rel="stylesheet">

    <style>

        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
            font-family:'Poppins',sans-serif;
        }

        body{
            background:#f4f5fb;
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            align-items: start;
        }

        .studentListContainer{
            padding:0px 12px;
            width: 80%;
        }

        .studentListHeading{
            font-size:40px;
            font-weight:700;
            color:#111827;
        }

        .studentListSubtitle{
            color:#667085;
            margin-top:4px;
            margin-bottom:10px;
        }

        .studentSearchBox{
            width:100%;
            height:56px;
            border:none;
            outline:none;
            background:#ffffff;
            border-radius:18px;
            padding:0 20px;
            border:1px solid #ececec;
            margin-bottom:24px;
            font-size:16px;
        }

        .studentTableCard{
            background:#ffffff;
            border-radius:30px;
            overflow-y:scroll;
            height: 560px;
            border:1px solid #ececec;
        }

        .studentTable{
            width:100%;
            border-collapse:collapse;
        }

        .studentTable thead{
            background:#f5f6fb;
        }

        .studentTable th{
            padding:10px 20px;
            text-align:left;
            color:#98a2b3;
            font-size:13px;
        }

        .studentTable td{
            padding:10px 20px;
            font-size: 14px;
            border-top:1px solid #ececec;
        }

        .studentName{
            font-weight:600;
            color:#111827;
        }

        .studentStatusBadge{
            display:inline-block;
            padding:7px 14px;
            border-radius:30px;
            font-size:13px;
            font-weight:600;
        }

        .studentActive{
            background:#dcfff6;
            color:#00c896;
        }
        
        .attendanceTopbar {
    width: 100%;
    background: #ffffff;
    padding: 12px 28px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid #e7e7e7;
    }
    
    .attendanceTeacherProfile {
    display: flex;
    align-items: center;
    gap: 12px;
    background: #f5f6fb;
    padding: 10px 14px;
    border-radius: 16px;
}

.attendanceTeacherAvatar {
    width: 42px;
    height: 42px;
    border-radius: 50%;
    background: linear-gradient(to right, #675cff, #1dd5c8);
    display: flex;
    align-items: center;
    justify-content: center;
    color: #ffffff;
    font-weight: 600;
}

    </style>

</head>

  <body>
    <jsp:include page="sidebar.jsp" />

    <div class="studentListContainer">
      <div class="attendanceTopbar">
        <h2 class="attendanceTopbarTitle">Students</h2>
        <div class="attendanceTeacherProfile">
          <div class="attendanceTeacherAvatar">T</div>
          <div>
            <h4>Teacher</h4>
            <p class="attendanceTeacherRole">Science Department</p>
          </div>
        </div>
      </div>

      <h1 class="studentListHeading">Student List</h1>
      <p class="studentListSubtitle">View all enrolled students</p>

      <div class="studentTableCard">
        <table class="studentTable">
          <thead>
            <tr>
              <th>ROLL NO</th>
              <th>FULL NAME</th>
              <th>CLASS</th>
              <th>PARENT</th>
              <th>PHONE</th>
              <th>STATUS</th>
            </tr>
          </thead>

          <tbody>

          </tbody>
        </table>
      </div>
    </div>
    <script type="text/javascript" src="studentList.js"></script>
  </body>

</html>