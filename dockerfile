# =======================
# Stage 1: Build
# =======================
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

# Copia apenas arquivos de configuração primeiro (para cache das dependências)
COPY gradlew .
COPY gradle ./gradle
COPY build.gradle settings.gradle ./

# Dá permissão para o wrapper
RUN chmod +x gradlew

# Baixa dependências e prepara o cache
RUN ./gradlew build -x test --no-daemon || true

# Copia todo o código fonte
COPY . .

# Gera o bootJar do Spring Boot
RUN ./gradlew bootJar --no-daemon

# =======================
# Stage 2: Runtime
# =======================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Porta padrão do Render
ENV PORT 8080
EXPOSE $PORT

# Copia o jar gerado pelo stage de build
COPY --from=build /app/build/libs/*.jar app.jar

# Roda a aplicação usando a porta definida pelo Render
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=$PORT"]
