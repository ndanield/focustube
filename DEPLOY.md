# Deploy gratis de FocusTube

Esta app esta lista píara desplegarse en hosting gratis como Render o Railway.

## 1) Variables de entorno requeridas

Configura estas variables en tu hosting:

- FOCUSTUBE_GOOGLE_CLIENT_ID
- FOCUSTUBE_GOOGLE_CLIENT_SECRET
- YOUTUBE_API_KEY

## 2) Render (recomendado)

### Opcion A: usando render.yaml

1. Sube este proyecto a GitHub.
2. En Render, crea un "New Web Service" desde tu repo.
3. Si Render detecta [render.yaml](render.yaml), acepta la configuracion.
4. Carga los valores de las 3 variables de entorno.
5. Deploy.

### Opcion B: configuracion manual

- Runtime: Java
- Build Command: `./mvnw clean package -DskipTests`
- Start Command: `java -Dserver.port=$PORT -jar target/*.jar`
- Plan: Free

## 3) Railway

1. Crea un nuevo proyecto desde GitHub.
2. Define las mismas 3 variables de entorno.
3. Railway usara Maven automaticamente y puede usar [Procfile](Procfile) para iniciar.

Si necesitas definir comando manual:

- Build: `./mvnw clean package -DskipTests`
- Start: `java -Dserver.port=$PORT -jar target/*.jar`

## 4) Google OAuth (importante)

En Google Cloud Console, en OAuth Client:

- Agrega URL de app en produccion como Authorized JavaScript origins.
- Agrega la URL de callback:
  - `https://TU_DOMINIO/login/oauth2/code/google`

Sin esto, el login de Google va a fallar en produccion.

## 5) Verificacion rapida post-deploy

- Abre la URL publica.
- Verifica que carga el titulo FocusTUBE.
- Inicia sesion con Google.
- Busca un video para validar acceso a YouTube API.
