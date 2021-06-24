# Step 1
FROM node:14 as build-step

RUN mkdir /app

WORKDIR /app

COPY package.json /app

RUN npm config set package-lock false
#RUN npm clean-install
RUN npm install

COPY . /app

RUN npm run build

# Stage 2
FROM nginx

COPY --from=build-step /app/build /usr/share/nginx/html