$(document).ready(function () {
    $('#table-users').DataTable({
        'ajax': {
            'contentType': 'application/json',
            'url': '/dashboard/admin/users/list',
            'type': 'POST',
            'data': function (d) {
                return JSON.stringify(d);
            }
        },
        'columns': [
            {'data': 'userId'},
            {'data': 'user.username'},
            {'data': 'user.email'},
            {'data': 'user.status'},
            {'data': 'createdDate'},
            {'data': null}
        ],
        'order': [[1, 'asc']],
        'columnDefs': [
            {targets: 4, render: function (data) { 
                    return moment(data).format('YYYY-MM-DD HH:mm:ss');
                }
            },
            {targets: 5, render: function (data, type, row, meta) { 
                    return data =   '<a class="pencil" href=/dashboard/admin/users/edit/' + row.userId + '><i class="fas fa-pencil-alt"></i></a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+
                                    '<a class="delete-user trash" data-id=' + row.userId + ' href=#><i class="fas fa-trash-alt"></i></a>';
                }
            }
        ]
    });
    
    $('#table-users tbody').on('click', '.delete-user', function() {
        var ctx = $(this);
        $.ajax({
            url: '/dashboard/admin/users/delete/' + ctx.data('id'),
            type: 'DELETE',
            success: function(result) {
                ctx.closest("tr").fadeOut(500, function(){ 
                    $(this).remove();                    
                }); ;
            }
        });
    });
});


