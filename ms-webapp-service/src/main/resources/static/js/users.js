$(document).ready(function () {
    $('#table-users').DataTable({
        'ajax': {
            'contentType': 'application/json',
            'url': '/dashboard/admin/users/list',
            'type': 'POST',
            'data': function (d) {
                d.columns.splice(5, 1);
                return JSON.stringify(d);
            }
        },
        'columns': [
            {'data': 'userId'},
            {'data': 'user.username'},
            {'data': 'user.email'},
            {'data': 'user.status'},
            {'data': 'user.createdDate', render: function (data) {
                    return moment(data).format('YYYY-MM-DD HH:mm:ss');
                }
            },
            {'data': null, 'orderable': false, 'searchable': false, render: function (data, type, row, meta) {
                    return data = '<a class="pencil" href=/dashboard/admin/users/edit/' + row.userId + '><i class="fas fa-pencil-alt"></i></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp' +
                            '<a class="delete-user trash" data-id=' + row.userId + ' href=#><i class="fas fa-trash-alt"></i></a>';
                }
            }
        ],
        'serverSide': true,
        'order': [[1, 'asc']]
    });

    $('#table-users tbody').on('click', '.delete-user', function () {
        var ctx = $(this);
        $.ajax({
            url: '/dashboard/admin/users/delete/' + ctx.data('id'),
            type: 'DELETE',
            success: function (result) {
                ctx.closest("tr").fadeOut(500, function () {
                    $(this).remove();
                });
                ;
            }
        });
    });
    
});


