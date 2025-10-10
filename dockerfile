# ===============================
# Etapa 1: Build com Gradle
# ===============================
FROM gradle:8.10.2-jdk21-alpine AS build
WORKDIR /app

# Copia tudo e faz o build
COPY . .
RUN gradle clean build -x test

# ===============================
# Etapa 2: Runtime (imagem leve)
# ===============================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia o JAR gerado da etapa anterior
COPY --from=build /app/build/libs/*.jar app.jar

# Define variáveis de ambiente
ENV PORT=8080
ENV JAVA_OPTS=""

# Expõe a porta
EXPOSE 8080

# Comando de inicialização
CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar --server.port=$PORT"]
