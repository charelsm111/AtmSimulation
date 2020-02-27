<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container">
        <h1>Welcome: ${sessionScope.account.name} </h1>
        <br />
        <a href="#" id="logout">Logout</a>
    </div>

    <script src="/js/jquery.min.js"></script>
    <script>
        $(document).ready(() => {
            $("#logout").click(() => {
                $.post("/logout", function( data ) {
                    $(location).attr('href', '/');
                });
            });
        });
    </script>
</body>
</html>