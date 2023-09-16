FROM node:18-alpine


WORKDIR /frontend


COPY package.json yarn.lock* package-lock.json* pnpm-lock.yaml* ./


RUN npm ci

COPY . ./


CMD npm run dev;