<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1>Welcome</h1>
        <form action="/login" method="POST">
          <div class="form-group">
            <label for="accountNumber">Email address</label>
            <input type="text" class="form-control" id="accountNumber" name="accountNumber" placeholder="Enter account number">
          </div>
          <div class="form-group">
            <label for="pin">PIN</label>
            <input type="password" class="form-control" id="pin" name="pin" placeholder="PIN">
          </div>
          <button type="submit" class="btn btn-primary">Sign In</button>
        </form>
    </div>
</body>
</html>