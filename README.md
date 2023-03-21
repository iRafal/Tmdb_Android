# Tmdb

## The goal
The goal is to create a sample app, where I can try to use new android related technologies and experiment with libraries.

## The App Topic
The app topic is a Movie. App is a clone of The Movie Data Base (TMDB) service mobile client.

## Architecture
- App logic is based on MVI architecture. Some kind of REDUX web architecture was used here. 
- App Ui is based on Compose.
- Local cache is represented via DB: ROOM, Object Box, Realm (can be switched).
- Remote connection with server is based on KTOR, Retroft (can be switched).
- Unit and Android Inctrumental (DB tests) tests are present (Compose UI tests not added uet, UI work is in progress).

## Current state
- Created a home screen with different movies sections (most popular, most viewed, e.t.c).
- Created movie details screen and navigation from home page to it.
- Added Api layer
- Added Api response data caching

## TODO
- Implement more features (screens)
- Finish Movie Details page
- Extend Api layer with new api calls
- Finalize architecture
- Try to add more tools to the project (ktlint, githab actions, and other which will be useful to try or to use)
