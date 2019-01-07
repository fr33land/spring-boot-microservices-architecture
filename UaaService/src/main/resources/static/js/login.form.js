$(document).ready(function () {
    $("#login-form").validate({
        onkeyup: false,
        errorElement: "span",
        errorClass: "is-invalid",
        rules: {
            username: "required",
            password: "required"
        },
        messages: {
            username: "Please enter your username",
            password: "Please enter your password"
        }
    });

    $("#register-form").validate({
        onkeyup: false,
        errorElement: "span",
        errorClass: "is-invalid",
        rules: {
            email: {
                required: true,
                validemail: true,
                minlength: 5
            },
            password: {
                required: true,
                minlength: 6
            },
            passwordconfirm: {
                required: true,
                minlength: 6,
                equalTo: "#passwd"
            }
        },
        messages: {
            email: {
                required: "Please enter a valid email address",
                minlength: "Your email must consist of at least 5 characters"
            },
            password: {
                required: "Please provide a password",
                minlength: "Your password must be at least 6 characters long"
            },
            passwordconfirm: {
                required: "Please provide a password",
                minlength: "Your password must be at least 6 characters long",
                equalTo: "Please enter the same password as above"
            }
        }
    });
});
	