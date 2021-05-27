# standings-backend

https://apifootball.com/documentation/#Competitions

### Sample curl 
```
curl --location --request GET 'localhost:8080/standings?countryName=England&leagueName=Non%20League%20Premier&teamName=Folkestone%20Invicta'
```

### Sample response 
```json
{
    "success": true,
    "data": {
        "countryId": 44,
        "countryName": "England",
        "leagueId": 149,
        "leagueName": "Non League Premier",
        "teamId": 3033,
        "teamName": "Folkestone Invicta",
        "overallLeaguePosition": 10
    }
}
```