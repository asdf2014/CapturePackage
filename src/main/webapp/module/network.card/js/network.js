$('#network').bootstrapTable({
    url: '/network/cards',
    columns: [{
        field: 'name',
        title: 'Name'
    }, {
        field: 'description',
        title: 'Description'
    }, {
        field: 'addresses',
        title: 'Addresses'
    }]
});

// private String name;
// private String description;
// private String addresses;