FROM openjdk:11 as builder
COPY . .
RUN ./gradlew build -x test --parallel

FROM openjdk:11
COPY --from=builder ./groove-api/build /api
COPY --from=builder ./entrypoint.sh /api
RUN chmod +x /api/entrypoint.sh
ENTRYPOINT ["/api/entrypoint.sh"]
