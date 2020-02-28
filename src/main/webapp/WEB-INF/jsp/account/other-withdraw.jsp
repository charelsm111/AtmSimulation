<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/main.css" rel="stylesheet">
</head>

<body>
    <div class="container">
        <div class="row top5">
            <div class="col-sm text-center">
                <h1>Ohter Withdraw</h1>
            </div>
        </div>
        <br />
        <form action="/withdraw" method="post">
            <div class="row justify-content-sm-center">
                <div class="col-sm-6 text-center">
                    <div class="form-group">
                        <label for="amount">Amount</label>
                        <input type="text" class="form-control" id="amount" name="amount" placeholder="Enter amount">
                    </div>
                </div>
            </div>
            <div class="row justify-content-sm-center top5">
                <div class="col-sm-6 text-center">
                    <input type="submit" value="Submit" class="btn btn-primary btn-lg btn-block withdraw">
                </div>
            </div>
        </form>
    </div>
</body>
</html>