Maven används för dependencies

Databashanterare är postgresql, https://www.postgresql.org/download/
Hårdkodat i DBController används användare för postgresql användarnamn: postgres, lösenord: password
Kör filen db.sql i src\main\resources\db med kommando \i (sökväg)/db.sql (viktigt med forwardslash i windows)

Starta servern med APIServer.main() och öppna localhost/4040/ för att visa appen