# Use official Node.js image as base
FROM node:18

# Set working directory inside container
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy all files to container
COPY . .

# Expose port
EXPOSE 3000

# Start the application
CMD ["npm", "start"]
