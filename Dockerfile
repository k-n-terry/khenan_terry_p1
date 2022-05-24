# script for image

# FROM base-image

FROM openjdk
# copy all files in current workspace ->  virtual env.

ENV KHENAN_TERRY_P1="jdbc:postgresql://revature-kterry-db.cwyqcu7gmbtm.us-east-1.rds.amazonaws.com/khenan_terry_p1?user=postgres&password=ArCo1ArCo1"

COPY . /workspace

# which port does the app. run on
EXPOSE 5000

# specify directory in the env, where you want to work.
WORKDIR /workspace

# command to run
ENTRYPOINT ["java","-jar","./target/khenan_terry_p1_REST_API-1.0-SNAPSHOT.jar"]
