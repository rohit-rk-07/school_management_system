<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Assign Students UI</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">

    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
        rel="stylesheet">
        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css" integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    <style>

        *{
            margin:0;
            padding:0;
            box-sizing:border-box;
            font-family:'Poppins',sans-serif;
        }

        body{
            background:#f4f5fb;
            color:#111827;
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            align-items: start;
        }


        .assignStudentTopbar{
            width:100%;
            background:#ffffff;
            padding:12px 28px;
            display:flex;
            justify-content:space-between;
            align-items:center;
            border-bottom:1px solid #e7e7e7;
        }

        .assignStudentTopbarTitle{
            font-size:20px;
            font-weight:600;
        }

        .assignStudentAdminProfile{
            display:flex;
            align-items:center;
            gap:12px;
            background:#f5f6fb;
            padding:10px 14px;
            border-radius:16px;
        }

        .assignStudentAdminAvatar{
            width:42px;
            height:42px;
            border-radius:50%;
            background:linear-gradient(to right,#675cff,#1dd5c8);
            display:flex;
            align-items:center;
            justify-content:center;
            color:#ffffff;
            font-weight:600;
        }

        .assignStudentAdminRole{
            font-size:13px;
            color:#8c8c8c;
        }


        .assignStudentPageContainer{
            padding:15px 12px;
            width: 80%;
        }

        .assignStudentPageHeading{
            font-size:40px;
            font-weight:700;
        }

        .assignStudentPageSubtitle{
            color:#667085;
            margin-top:4px;
            margin-bottom:10px;
        }


        .assignStudentFormCard{
            background:#ffffff;
            border-radius:30px;
            padding:10px 20px;
            border:1px solid #ececec;
            margin-bottom:18px;
        }

        .assignStudentFormGrid{
            display:grid;
            grid-template-columns:1fr 1fr;
            gap:20px;
        }

        .assignStudentFormGroup{
            display:flex;
            flex-direction:column;
        }

        .assignStudentFormLabel{
            margin-bottom:10px;
            font-size:15px;
            font-weight:600;
            color:#5f6b85;
            letter-spacing:0.5px;
        }

        .assignStudentSelectInput{
            width:100%;
            height:54px;
            border:none;
            outline:none;
            background:#f5f6fb;
            border-radius:14px;
            padding:0 18px;
            font-size:17px;
            border:1px solid #e2e5ef;
            color:#111827;
            cursor:pointer;
        }


        .assignStudentButton{
            margin-top:14px;
            border:none;
            background:#5c4ef7;
            color:#ffffff;
            padding:10px 20px;
            border-radius:14px;
            font-size:15px;
            font-weight:600;
            cursor:pointer;
            transition:0.3s;
            box-shadow:0 10px 20px rgba(92,78,247,0.2);
        }

        .assignStudentButton:hover{
            background:#4e40ee;
        }


        .assignStudentTableCard{
            background:#ffffff;
            border-radius:30px;
            overflow-y: scroll;
            height: 410px;
            border:1px solid #ececec;
        }


        .assignStudentDataTable{
            width:100%;
            border-collapse:collapse;
            overflow-y: scroll;
        }

        .assignStudentDataTable thead{
            background:#f5f6fb;
        }

        .assignStudentDataTable th{
            text-align:left;
            padding:10px 22px;
            font-size:13px;
            color:#98a2b3;
            font-weight:600;
        }

        .assignStudentDataTable td{
            padding:9px 20px;
            border-top:1px solid #ececec;
            font-size:15px;
        }


        .assignStudentDeleteButton{
            width:42px;
            height:42px;
            border:none;
            border-radius:12px;
            background:#f5f6fb;
            cursor:pointer;
            font-size:18px;
            color: red;
        }
        
        #fullName{
        	font-weight: 600;
        }
        
        #rollNumber{
        	display: inline-block;
    		padding: 7px 14px;
    		border-radius: 30px;
    		background: #fff2df;
    		color: #ff9900;
    		font-size: 14px;
    		font-weight: 600;
        }


        @media(max-width:768px){

            .assignStudentFormGrid{
                grid-template-columns:1fr;
            }

            .assignStudentDataTable{
                display:block;
                overflow-x:auto;
            }

        }

    </style>

</head>

<body>

	<jsp:include page="../sidebar.jsp" />

    <div class="assignStudentPageContainer">
    
    <div class="assignStudentTopbar">

        <h2 class="assignStudentTopbarTitle">
            Assign Students
        </h2>

        <div class="assignStudentAdminProfile">

            <div class="assignStudentAdminAvatar">
                A
            </div>

            <div>

                <h4>Admin</h4>

                <p class="assignStudentAdminRole">
                    Super Admin
                </p>

            </div>

        </div>

    </div>

        <h1 class="assignStudentPageHeading">
            Assign Students
        </h1>

        <p class="assignStudentPageSubtitle">
            Enroll students into classes
        </p>


        <div class="assignStudentFormCard">
        <form action="">
            <div class="assignStudentFormGrid">

                <div class="assignStudentFormGroup">
                    <label class="assignStudentFormLabel">SELECT STUDENT</label>

                    <select class="assignStudentSelectInput" id="studentSelect">
                        <option>Choose Student</option>
                        
                    </select>
                </div>

                <div class="assignStudentFormGroup" >
                    <label class="assignStudentFormLabel">SELECT CLASS</label>

                    <select class="assignStudentSelectInput" id="classSelect">
                        <option>Choose Class</option>
                        
                    </select>
                </div>
            </div>
            <button class="assignStudentButton" id="assignStudentBtn">Assign Student</button>
		</form>
        </div>

        <div class="assignStudentTableCard">
            <table class="assignStudentDataTable">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>STUDENT</th>
                        <th>CLASS</th>
                        <th>ASSIGNED ON</th>
                        <th>ROLL NUMBER</th>
                        <th>ACTION</th>
                    </tr>
                </thead>
                <tbody id="enrollmentTableBody">
                    <tr>
                        <td>1</td>
                        <td>Aarav Kumar</td>
                        <td>Grade 10 - A</td>
                        <td>2024-06-01</td>
                        <td><button class="assignStudentDeleteButton">🗑</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

<script type="text/javascript" src="assignStudent.js"></script>
</body>

</html>