FROM openjdk:11
COPY ./groove-api/build /api
COPY ./entrypoint.sh /api
RUN chmod +x /api/entrypoint.sh
ENTRYPOINT ["/api/entrypoint.sh"]
