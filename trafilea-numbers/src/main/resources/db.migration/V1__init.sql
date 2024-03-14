CREATE TABLE IF NOT EXISTS public.trafilea_numbers(
    number_id bigint NOT NULL,
    number_type text
);

ALTER TABLE ONLY public.trafilea_numbers
    ADD CONSTRAINT number_id_pkey PRIMARY KEY (number_id);

