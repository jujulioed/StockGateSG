# Imagem base
FROM node:22-alpine AS base

WORKDIR /app

# Compatibilidade com algumas dependências nativas do Node
RUN apk add --no-cache libc6-compat


# ==============================
# Etapa 1: Instala dependências
# ==============================
FROM base AS dependencies

COPY package.json yarn.lock ./

RUN yarn install --frozen-lockfile


# ==============================
# Etapa 2: Compila a aplicação
# ==============================
FROM base AS build

COPY --from=dependencies /app/node_modules ./node_modules

COPY . .

# Variável pública utilizada pelo navegador
ARG NEXT_PUBLIC_API_URL

ENV NEXT_PUBLIC_API_URL=${NEXT_PUBLIC_API_URL}
ENV NEXT_TELEMETRY_DISABLED=1

RUN yarn build


# ==============================
# Etapa 3: Executa a aplicação
# ==============================
FROM node:22-alpine AS production

WORKDIR /app

ENV NODE_ENV=production
ENV NEXT_TELEMETRY_DISABLED=1
ENV HOSTNAME=0.0.0.0
ENV PORT=3000

# Cria usuário não-root
RUN addgroup --system --gid 1001 nodejs \
    && adduser --system --uid 1001 nextjs

# Arquivos públicos
COPY --from=build --chown=nextjs:nodejs /app/public ./public

# Arquivos gerados pelo modo standalone
COPY --from=build --chown=nextjs:nodejs /app/.next/standalone ./

# Arquivos estáticos gerados pelo Next
COPY --from=build --chown=nextjs:nodejs /app/.next/static ./.next/static

USER nextjs

EXPOSE 3000

CMD ["node", "server.js"]