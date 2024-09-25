# FROM node:alpine as build

# WORKDIR /usr/src/app

# COPY package.json package-lock.json ./
# RUN npm install

# COPY . .
# RUN npm run build --prod

# FROM nginx:stable-alpine
# COPY --from=build /usr/src/app/dist /usr/share/nginx/html
# COPY nginx.conf /etc/nginx/conf.d/default.conf
# EXPOSE 8000
# CMD [ "nginx", "-g", "daemon off;" ]


# Stage 1: Build
FROM node:alpine as build

WORKDIR /usr/src/app

COPY package.json package-lock.json ./
RUN npm install

COPY . .
RUN npm run build --prod

# Stage 2: Serve
FROM nginx:stable-alpine
COPY --from=build /usr/src/app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
