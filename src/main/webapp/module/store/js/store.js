$('#store').bootstrapTable({
    url: '/capture/store',
    columns: [{
        field: 'message',
        title: 'Message'
    }]
});