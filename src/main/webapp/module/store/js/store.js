$('#store').bootstrapTable({
    url: '/capture/store',
    columns: [{
        field: 'fileName',
        title: 'File Name'
    }, {
        field: 'modifyDate',
        title: 'Modify Date'
    }, {
        field: 'path',
        title: 'Path'
    }]
});

// private String fileName;
// private String modifyDate;
// private String path;