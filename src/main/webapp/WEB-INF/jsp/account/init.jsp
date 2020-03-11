<%@ include file="../partial/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Account Initialization</title>
    <%@ include file="../partial/header.jsp" %>
</head>

<body>
    <div class="container">
        <div class="row top5">
            <div class="col-sm text-center">
                <h1>Account Initialization</h1>
            </div>
        </div>
        <hr />

        <div class="row top5">
            <div class="col-sm text-center">
                <p>You can add more accounts or skip to login page</p>
            </div>
        </div>

        <div class="row justify-content-sm-center top5">
            <div class="col-sm-6 text-center">
                <a href="/login" class="btn btn-success btn-lg btn-block withdraw">Login</a>
            </div>
        </div>

        <hr />
        <div class="row top5">
            <div class="col-sm text-center">
                <h2>Add more accounts</h2>
                <small>CSV Format: name, account number, balance, pin</small>
                <p><small class="text-danger">and without header</small><p>
            </div>
        </div>

        <div class="row top5">
            <div class="col-sm text-center">
                <div class="errormsg">${message}</div>
            </div>
        </div>

        <br />
        <form:form id="form-account-init" action="/account/init" enctype="multipart/form-data" method="post">
            <div class="row justify-content-sm-center">
                <div class="col-sm-6 text-center">
                    <div class="form-group">
                        <label for="file">File</label>
                        <input name="file" path="file" class="form-control-file" type="file" id="file" placeholder="Choose a file..." />
                    </div>
                </div>
            </div>
            <div class="row justify-content-sm-center top5">
                <div class="col-sm-6 text-center">
                    <input type="submit" value="Submit" class="btn btn-primary btn-lg btn-block withdraw">
                </div>
            </div>
        </form:form>
    </div>

    <%@ include file="../partial/footer.jsp" %>
</body>
</html>