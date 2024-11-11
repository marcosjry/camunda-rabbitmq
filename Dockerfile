#FROM camunda/camunda-bpm-platform:run-latest
FROM camunda/camunda-bpm-platform:7.21.0

#Copia o JAR do connector para a pasta /internal/webapps do Camunda
COPY target/currency-connector-1.0.1.jar /camunda/lib
#COPY target/currency-connector-1.0.1.jar /camunda/internal/webapps/
#/camunda/webapps/camunda/WEB-INF/lib/

# Inicia o servidor
CMD ["./camunda.sh"]