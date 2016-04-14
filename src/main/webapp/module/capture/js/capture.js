$('#capture').bootstrapTable({
    url: '/capture/infos',
    columns: [{
        field: 'version',
        title: 'Version'
    }, {
        field: 'ihl',
        title: 'IHLe'
    }, {
        field: 'tos',
        title: 'TOS'
    }, {
        field: 'totalLength',
        title: 'Total length'
    }, {
        field: 'identification',
        title: 'Identification'
    }, {
        field: 'reservedFlag',
        title: 'Flags'
    }, {
        field: 'fragmentOffset',
        title: 'Fragment offset'
    }, {
        field: 'ttl',
        title: 'TTL'
    }, {
        field: 'protocol',
        title: 'Protocol'
    }, {
        field: 'headerChecksum',
        title: 'Header checksum'
    }, {
        field: 'srcAddr',
        title: 'Source address'
    }, {
        field: 'dstAddr',
        title: 'Destination address'
    }]
});