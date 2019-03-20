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
            username: {
                required: true,
                minlength: 5
            },
            email: {
                required: true,
                validemail: true,
                minlength: 5
            },
            password: {
                required: true,
                validpassword: true
            },
            passwordconfirm: {
                required: true,
                equalTo: "#passwd"
            }
        },
        messages: {
            username: {
                required: "Please enter username",
                minlength: "Your username must consist of at least 5 characters"
            },
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

    $("#resend-form").validate({
        onkeyup: false,
        errorElement: "span",
        errorClass: "is-invalid",
        rules: {
            email: {
                required: true,
                validemail: true,
                minlength: 5
            }
        },
        messages: {
            email: {
                required: "Please enter a valid email address",
                minlength: "Your email must consist of at least 5 characters"
            }
        }
    });

    $("#password-reset-form").validate({
        onkeyup: false,
        errorElement: "span",
        errorClass: "is-invalid",
        rules: {
            email: {
                required: true,
                validemail: true,
                minlength: 5
            }
        },
        messages: {
            email: {
                required: "Please enter a valid email address",
                minlength: "Your email must consist of at least 5 characters"
            }
        }
    });

    $("#password-change-form").validate({
        onkeyup: false,
        errorElement: "span",
        errorClass: "is-invalid",
        rules: {
            password: {
                required: true,
                validpassword: true,
            },
            passwordconfirm: {
                required: true,
                equalTo: "#passwd"
            }
        },
        messages: {
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

    $('input[data-toggle="popover"]').popover({
        placement: 'right',
        trigger: 'focus'
    });

});