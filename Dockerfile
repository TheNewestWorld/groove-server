FROM openjdk:11 as builder
COPY . .
RUN ./gradlew build --parallel

FROM openjdk:11
COPY --from=builder ./groove-api/build /api
COPY --from=builder ./entrypoint.sh /api
COPY --from=builder ./storage/src/main/resources/Wallet_CV5WKXST4FVGYIXN /api/oracle-wallet

RUN chmod +x /api/entrypoint.sh
ENTRYPOINT ["/api/entrypoint.sh"]
