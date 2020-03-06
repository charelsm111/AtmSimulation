<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/main.css" rel="stylesheet">
    <link href="/css/jquery-ui.css" rel="stylesheet">
</head>

<body>
    <div class="container">
        <div class="row top5">
            <div class="col-sm text-center">
                <h1>History</h1>
            </div>
        </div>
        <br />
        <form id="form-history" action="/history" method="get">
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <input type="text" class="form-control" id="transaction" name="transaction" placeholder="Pick a date">
                    </div>
                </div>
                <div class="col-sm-2">
                    <input type="submit" value="Find" class="btn btn-primary">
                </div>
            </div>
        </form>
        <hr />
        <table class="table">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Type</th>
              <th scope="col">Date</th>
              <th scope="col">Amount</th>
              <th scope="col">Destination Account</th>
            </tr>
          </thead>
          <tbody>
            <% int counter = 1; %>
            <c:forEach items="${transactions}" var="transaction">
            <tr>
              <th scope="row"><%= counter++ %></th>
              <td>${transaction.type}</td>
              <td>${transaction.date}</td>
              <td>${transaction.amount}</td>
              <td>${transaction.destinationAccountNumber}</td>
            </tr>
            </c:forEach>
        </table>
    </div>

    <script src="/js/jquery.min.js"></script>
    <script src="/js/jquery-ui.min.js"></script>
    <script>
        $(document).ready(() => {
            $(() => {
                $("#transaction").datepicker({ dateFormat: 'yy-mm-dd' });
            });
        });
      </script>
</body>
</html>