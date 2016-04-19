netsh wlan show drivers
netsh wlan set hostednetwork mode=allow ssid=benedict key=12345678
netsh wlan start hostednetwork
netsh wlan stop hostednetwork

arp -a