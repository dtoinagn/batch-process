FROM public.ecr.aws/lambda/java:11
COPY target/classes ${LAMBDA_TASK_ROOT}
COPY target/dependency/* ${LAMBDA_TASK_ROOT}/lib/
RUN rm ${LAMBDA_TASK_ROOT}/lib/junit*.jar
CMD ["com.tiandn.batch.lambda.LambdaHandler::handleRequest"]