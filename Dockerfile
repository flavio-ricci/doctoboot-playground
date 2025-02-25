# Will be updated with an automation of docto-tooling-bot
ARG BASE_VERSION=21-jre-bookworm-20240925_131241@sha256:e8dd3adaae7646ea1355d7bb4dbfed8f9eec908103ca0837cfa5a32ddd951283
ARG ROOT_IMAGE=580698825394.dkr.ecr.eu-central-1.amazonaws.com/java

FROM ${ROOT_IMAGE}:${BASE_VERSION}

SHELL ["/bin/bash", "-o", "pipefail", "-c"]

WORKDIR /home/java

COPY target/doctoboot-playground*.jar /doctoboot-playground.jar
# FIXME: add the expected jar to copy from the builder

# https://docs.datadoghq.com/tracing/trace_collection/library_config/java/
ARG GIT_COMMIT_SHA1
ENV GIT_COMMIT_SHA1=${GIT_COMMIT_SHA1} \
  DD_VERSION=${GIT_COMMIT_SHA1} \
  DD_GIT_COMMIT_SHA=${GIT_COMMIT_SHA1} \
  DD_GIT_REPOSITORY_URL="github.com/doctolib/doctoboot-playground" \
  DD_TAGS="application:doctoboot-playground,team:deus" \
  DD_TRACE_OTEL_ENABLED=true \
  JAR_TO_RUN=/doctoboot-playground.jar \
  spring_profiles_active=prod
USER java
