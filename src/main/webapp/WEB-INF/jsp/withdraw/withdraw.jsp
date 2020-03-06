<%@ include file="../partial/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Withdraw</title>
    <%@ include file="../partial/header.jsp" %>
</head>

<body>
    <div class="container">
        <div class="row top5">
            <div class="col-sm text-center">
                <h1>Withdraw</h1>
            </div>
        </div>
        <br />
        <p><a href="/account" class="btn btn-warning">Back</a></p>
        <div class="row top5">
            <div class="col-sm text-center">
                <form:errors path = "*" cssClass = "errorblock" element = "div" />
            </div>
        </div>

        <br />
        <div class="row justify-content-sm-center">
            <div class="col-sm-6 text-center">
                <a href="#" data-value="10" class="btn btn-primary btn-lg btn-block withdraw" role="button" aria-disabled="true">$10</a>
            </div>
        </div>
        <div class="row justify-content-sm-center top5">
            <div class="col-sm-6 text-center">
                <a href="#" data-value="50" class="btn btn-primary btn-lg btn-block withdraw" role="button" aria-disabled="true">$50</a>
            </div>
        </div>
        <div class="row justify-content-sm-center top5">
            <div class="col-sm-6 text-center">
                <a href="#" data-value="100" class="btn btn-primary btn-lg btn-block withdraw" role="button" aria-disabled="true">$100</a>
            </div>
        </div>
        <div class="row justify-content-sm-center top5">
            <div class="col-sm-6 text-center">
                <a href="/other-withdraw" id="other-withdraw" class="btn btn-secondary btn-lg btn-block" role="button" aria-disabled="true">Other</a>
            </div>
        </div>
        <br />
        <br />
        <div class="row justify-content-sm-center top5">
            <div class="col-sm-6 text-center">
                <a href="/account" class="btn btn-danger btn-lg btn-block" role="button" aria-disabled="true">Exit</a>
            </div>
        </div>
    </div>

    <%@ include file="../partial/footer.jsp" %>
    <script>
        $(document).ready(() => {
            $(".withdraw").click((event) => {
                var value = $(event.target).data('value');

                $.post("/withdraw",
                    {
                        amount: value,
                    },
                    (data) => {
                        if(data.valid){
                            $(location).attr('href', '/account');
                            alert("Success");
                        }else{
                            $.each(data.errorMessages, function(key, value) {
                                alert(value);
                            });
                        }
                    });
            });
        });
    </script>
</body>
</html>