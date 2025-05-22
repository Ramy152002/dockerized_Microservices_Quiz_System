# استخدم صورة أساسية تحتوي على OpenJDK 17.
# 'eclipse-temurin:17-jdk-jammy' خيار جيد لأنه خفيف ومبني على Ubuntu.
FROM eclipse-temurin:17-jdk-jammy

# تحديد دليل العمل داخل الحاوية.
WORKDIR /app

# تعريف متغير بيئة لاسم ملف JAR.
# هذا يجعل الـ Dockerfile أكثر مرونة إذا تغير اسم ملف JAR.
ARG JAR_FILE=target/*.jar
 # تأكد من أن هذا يطابق اسم ملف JAR الفعلي بعد البناء

# نسخ ملف JAR من نظام الملفات المحلي (حيث تقوم ببناء صورة Docker) إلى داخل الحاوية.
COPY ${JAR_FILE} app.jar

# تعريض المنفذ الذي تستمع عليه خدمة Eureka (المنفذ الافتراضي هو 8761).
#EXPOSE 8761

# تحديد الأمر الذي سيتم تشغيله عند بدء تشغيل الحاوية.
# هذا يشغل تطبيق Spring Boot JAR.
ENTRYPOINT ["java", "-jar", "app.jar"]