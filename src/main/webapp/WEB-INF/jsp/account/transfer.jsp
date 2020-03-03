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
                <h1>Transfer</h1>
            </div>
        </div>
        <br />
        <form id="form-transfer" action="/transfer" method="post">
            <div class="row justify-content-sm-center">
                <div class="col-sm-6 text-center">
                    <div class="form-group">
                        <label for="amount">Destination Account Number</label>
                        <input type="text" class="form-control" id="destination-account-number" name="destinationAccountNumber" placeholder="Enter destination account number">
                    </div>
                </div>
            </div>
            <div class="row justify-content-sm-center top5">
                <div class="col-sm-6 text-center">
                    <div class="form-group">
                        <button id="btn-check" class="btn btn-secondary btn-block">Check Account</button>
                    </div>
                </div>
            </div>
            <div class="row justify-content-sm-center top5">
                <div class="col-sm-6 text-center">
                    <div class="form-group">
                        <label for="amount">Amount</label>
                        <input type="text" class="form-control" id="amount" name="amount" placeholder="Enter amount" disabled>
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

    <script src="/js/jquery.min.js"></script>
    <script>
        $(document).ready(() => {
            // Check if account is exist
            $('#btn-check').click((e) => {
                var destinationAccountNumber = $('#destination-account-number').val();

                $.post("/check",
                    {destinationAccountNumber: destinationAccountNumber},
                    (data) => {
                        if(data.valid){
                            alert("Account valid");
                            $('#amount').prop('disabled', false);
                            $('#amount').focus();
                        }else{
                            $.each(data.errorMessages, function(key, value) {
                                alert(value);
                            });
                        }
                });
                e.preventDefault();
            })

            // Submit transfer
            $('#form-transfer').submit((e) => {
                var destinationAccountNumber = $('#destination-account-number').val();
                var amount = $('#amount').val();

                $.post("/transfer",
                    {amount: amount, destinationAccountNumber: destinationAccountNumber},
                    (data) => {
                        if(data.valid){
                            alert("Success");
                            $(location).attr('href', '/account');
                        }else{
                            $.each(data.errorMessages, function(key, value) {
                              alert(value);
                            });
                        }
                });
                e.preventDefault();
            });
        })
    </script>
</body>
</html>