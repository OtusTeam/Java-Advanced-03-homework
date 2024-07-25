
# DateSaverApplication
This app saved current date with zoneId(from request) to storage, and return list of saved dates.
If zoneId not found, use default zoneId from provider.

Developed on Java Module System.
Enjoy!

### Build packages
* mvn clean package*

### Run
*java -jar api/target/date-application.jar*

### Add new date to storage example
```
curl --location 'http://localhost:8081/date' \
--header 'Content-Type: application/json' \
--data 'Asia/Ho_Chi_Minh'
```

### Get all of saved dates from storage
```
curl --location 'http://localhost:8081/date'
```

zoneIds:
This maps as follows:
EST - -05:00
HST - -10:00
MST - -07:00
ACT - Australia/Darwin
AET - Australia/Sydney
AGT - America/Argentina/Buenos_Aires
ART - Africa/Cairo
AST - America/Anchorage
BET - America/Sao_Paulo
BST - Asia/Dhaka
CAT - Africa/Harare
CNT - America/St_Johns
CST - America/Chicago
CTT - Asia/Shanghai
EAT - Africa/Addis_Ababa
ECT - Europe/Paris
IET - America/Indiana/Indianapolis
IST - Asia/Kolkata
JST - Asia/Tokyo
MIT - Pacific/Apia
NET - Asia/Yerevan
NST - Pacific/Auckland
PLT - Asia/Karachi
PNT - America/Phoenix
PRT - America/Puerto_Rico
PST - America/Los_Angeles
SST - Pacific/Guadalcanal
VST - Asia/Ho_Chi_Minh