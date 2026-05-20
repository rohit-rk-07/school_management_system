<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Assigned Classes</title>

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
        }

        .assignedClassesContainer{
            padding:40px 32px;
        }

        .assignedClassesHeading{
            font-size:44px;
            font-weight:700;
            color:#111827;
        }

        .assignedClassesSubtitle{
            color:#667085;
            margin-top:4px;
            margin-bottom:30px;
        }

        .assignedClassesGrid{
            display:grid;
            grid-template-columns:repeat(auto-fit,minmax(320px,1fr));
            gap:20px;
        }

        .assignedClassesCard{
            background:#ffffff;
            border-radius:28px;
            padding:28px;
            border:1px solid #ececec;
            position:relative;
            overflow:hidden;
        }

        .assignedClassesCard::before{
            content:"";
            position:absolute;
            top:0;
            left:0;
            width:5px;
            height:100%;
            background:#675cff;
        }

        .assignedClassesBadge{
            display:inline-block;
            padding:8px 16px;
            border-radius:30px;
            background:#ecebff;
            color:#675cff;
            font-size:14px;
            font-weight:600;
            margin-bottom:20px;
        }

        .assignedClassesTitle{
            font-size:28px;
            font-weight:700;
            color:#111827;
            margin-bottom:6px;
        }

        .assignedClassesSubject{
            color:#667085;
            margin-bottom:24px;
            font-size:17px;
        }

        .assignedClassesInfo{
            margin-bottom:12px;
            color:#475467;
            font-size:16px;
        }

    </style>

</head>

<body>

    <div class="assignedClassesContainer">

        <h1 class="assignedClassesHeading">
            Assigned Classes
        </h1>

        <p class="assignedClassesSubtitle">
            Classes and subjects assigned to you
        </p>

        <div class="assignedClassesGrid">

            <div class="assignedClassesCard">

                <span class="assignedClassesBadge">
                    Science
                </span>

                <h2 class="assignedClassesTitle">
                    Class 8
                </h2>

                <p class="assignedClassesSubject">
                    Subject: Science
                </p>

                <p class="assignedClassesInfo">
                    👨‍🎓 Students: 32
                </p>

                <p class="assignedClassesInfo">
                    🕒 Timing: 9:00 AM
                </p>

                <p class="assignedClassesInfo">
                    📍 Room: 204
                </p>

            </div>

            <div class="assignedClassesCard">

                <span class="assignedClassesBadge">
                    Physics
                </span>

                <h2 class="assignedClassesTitle">
                    Class 9
                </h2>

                <p class="assignedClassesSubject">
                    Subject: Physics
                </p>

                <p class="assignedClassesInfo">
                    👨‍🎓 Students: 28
                </p>

                <p class="assignedClassesInfo">
                    🕒 Timing: 11:00 AM
                </p>

                <p class="assignedClassesInfo">
                    📍 Room: 301
                </p>

            </div>

        </div>

    </div>

</body>

</html>