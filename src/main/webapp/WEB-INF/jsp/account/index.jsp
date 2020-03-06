<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Account</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/main.css" rel="stylesheet">
</head>

<body>
    <div class="container">
        <div class="row top5">
            <div class="col-sm text-center">
                <h1>Welcome: ${sessionScope.account.name} </h1>
            </div>
        </div>
        <br />
        <div class="row">
            <div class="col-sm">
                <a href="/withdraw" class="btn btn-primary btn-lg btn-block" role="button" aria-disabled="true">Withdraw</a>
            </div>
            <div class="col-sm">
                <a href="/transfer" class="btn btn-secondary btn-lg btn-block" role="button" aria-disabled="true">Fund Transfer</a>
            </div>
        </div>

        <div class="row top5">
            <div class="col-sm">
                <a href="/history" class="btn btn-success btn-lg btn-block" role="button" aria-disabled="true">History</a>
            </div>
            <div class="col-sm">
                <a href="#" id="logout" class="btn btn-danger btn-lg btn-block" role="button" aria-disabled="true">Logout</a>
            </div>
        </div>
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