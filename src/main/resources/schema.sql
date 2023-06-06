DROP TABLE IF EXISTS public.student;
CREATE TABLE IF NOT EXISTS public.student(
    id SERIAL PRIMARY KEY,
    firstname VARCHAR(255),
    lastname VARCHAR(255)
);

DROP TABLE IF EXISTS public.course;
CREATE TABLE IF NOT EXISTS public.course(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    professor VARCHAR(255),
    semester VARCHAR(255),
    current_capacity int,
    max_capacity int
);

DROP TABLE IF EXISTS public.student_course;
CREATE TABLE IF NOT EXISTS public.student_course(
    id SERIAL PRIMARY KEY,
    student_id int,
    course_id int
);