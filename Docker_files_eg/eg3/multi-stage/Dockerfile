# Stage 1: Build React App
FROM node:18 AS builder
WORKDIR /app
COPY . .
RUN npm install && npm run build

# Stage 2: Serve using nginx
FROM nginx:alpine
COPY --from=builder /app/build /usr/share/nginx/html