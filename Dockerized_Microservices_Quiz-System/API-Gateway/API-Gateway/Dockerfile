# استخدم صورة أساسية تحتوي على OpenJDK 17.
FROM eclipse-temurin:17-jdk-jammy

# تحديد دليل العمل داخل الحاوية.
WORKDIR /app

# تعريف متغير بيئة لاسم ملف JAR.
ARG JAR_FILE=target/API-Gateway-0.0.1-SNAPSHOT.jar
# تأكد من أن هذا يطابق اسم ملف JAR الفعلي

# نسخ ملف JAR إلى داخل الحاوية.
COPY ${JAR_FILE} app.jar

# تعريض المنفذ الذي تستمع عليه بوابة الـ API (المنفذ الافتراضي هو 8080، أو أي منفذ تحدده).
# افترض 8080 كمنفذ افتراضي، يمكنك تعديله إذا كان لديك منفذ مختلف.
#EXPOSE 8080

# تحديد الأمر الذي سيتم تشغيله عند بدء تشغيل الحاوية.
ENTRYPOINT ["java", "-jar", "app.jar"]