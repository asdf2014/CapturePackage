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

$('#wifi_ip').bootstrapTable({
    url: '/network/wifi/ips',
    columns: [{
        field: 'deviceIP',
        title: 'Those IP address of device that connected with wifi'
    }]
});

$("#openWifi").click(function () {
    alert('open wiki...');
});
$("#closeWifi").click(function () {
    alert('close wiki...');
});