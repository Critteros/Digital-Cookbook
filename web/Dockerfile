FROM node:20.12-bookworm-slim AS node_base

ENV DEBIAN_FRONTEND=noninteractive
RUN apt update && apt install -y --no-install-recommends \
    git \
    build-essential

WORKDIR /app
RUN corepack enable
RUN yarn init -2
RUN yarn set version stable

COPY . .
RUN yarn install
RUN yarn build

EXPOSE 3000
ENV NODE_ENV=production

RUN addgroup --system --gid 1001 javaflavors-front
RUN adduser --system --uid 1001 javaflavors-front
USER javaflavors-front
CMD ["node", "build"]
